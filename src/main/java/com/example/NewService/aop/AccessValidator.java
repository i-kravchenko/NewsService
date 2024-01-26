package com.example.NewService.aop;

import com.example.NewService.model.User;
import com.example.NewService.model.UserReference;
import com.example.NewService.service.CommentService;
import com.example.NewService.service.NewsService;
import com.example.NewService.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.file.AccessDeniedException;
import java.text.MessageFormat;
import java.util.Arrays;

@Aspect
@Component
public class AccessValidator
{
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private NewsService newsService;

    @Before("@annotation(ValidateAccess) && execution(* com.example.NewService.service.*.save(..)) && args(entity)")
    public void beforeModify(UserReference entity) throws AccessDeniedException {
        Long user_id = getUserId();
        User user = userService.findById(user_id);
        if(user == null) {
            throw new AccessDeniedException(
                    MessageFormat.format("User with id: {0} not found", user_id)
            );
        }
        if(entity.getId() == null) {
            entity.setUser(user);
        } else if (entity.getUser() != null && !entity.getUser().getId().equals(user_id)) {
            throw new AccessDeniedException("Access denied");
        }
    }

    private Long getUserId() throws AccessDeniedException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String user_id = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("user_id"))
                .findFirst()
                .orElseThrow(() -> new AccessDeniedException("cookie user_id is required")).getValue();
        return Long.valueOf(user_id);
    }

    @Before("@annotation(ValidateAccess) && execution(* com.example.NewService.service.*.delete(..)) && args(id)")
    public void beforeDelete(JoinPoint joinPoint, Long id) throws AccessDeniedException {
        String serviceName = joinPoint.getSignature().getDeclaringType().getName();
        UserReference reference = null;
        if (serviceName.equals(CommentService.class.getName())) {
            reference = commentService.findById(id);
        } else if (serviceName.equals(NewsService.class.getName())) {
            reference = newsService.findById(id);
        }
        if(reference != null && reference.getUser()!= null && !reference.getUser().getId().equals(getUserId())) {
            throw new AccessDeniedException("Access denied");
        }
    }
}
