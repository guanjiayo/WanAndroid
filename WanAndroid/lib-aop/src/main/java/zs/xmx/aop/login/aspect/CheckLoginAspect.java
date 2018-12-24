package zs.xmx.aop.login.aspect;

import android.content.Context;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import androidx.fragment.app.Fragment;
import zs.xmx.aop.login.LoginAssistant;
import zs.xmx.aop.login.annotation.CheckLogin;
import zs.xmx.aop.login.callback.ILoginCallback;
import zs.xmx.aop.login.exception.AnnotationException;


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/11/7 19:18
 * @本类描述	  CheckLoginAspect 切面实现类
 * @内容说明
 *
 */
@Aspect
public class CheckLoginAspect {
    private static final String TAG = "CheckLoginAspect";

    private static final String PERMISSION_REQUEST_POINTCUT =
            "execution(@zs.xmx.aop.login.annotation.CheckLogin * *(..))";

    @Pointcut(PERMISSION_REQUEST_POINTCUT + " && @annotation(checkLogin)")
    public void pointCutCheckLogin(CheckLogin checkLogin) {
    }

    @Around("pointCutCheckLogin(checkLogin)")
    public void aroundCheckLogin(final ProceedingJoinPoint joinPoint, final CheckLogin checkLogin) throws Throwable {
        //1. 兼容Activity,Fragment,Service判断处理
        Context context = null;
        final Object object = joinPoint.getThis();
        if (object instanceof Context) {
            context = (Context) object;
        } else if (object instanceof Fragment) {
            context = ((Fragment) object).getActivity();
        } else if (object instanceof android.app.Fragment) {
            context = ((android.app.Fragment) object).getActivity();
        }
        if (context == null || checkLogin == null) {
            Log.e(TAG, "aroundJonitPoint error ");
            return;
        }

        //2.注解调用方式错误处理
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new AnnotationException("CheckLogin 注解只能用于方法上");
        }

        //3.根据登录状态的后续处理
        ILoginCallback callback = LoginAssistant.getInstance().getLoginCallBack();

        if (callback.isLogin(context)) {
            joinPoint.proceed();
        } else {
            callback.unLogin(context, checkLogin.tipType());
        }

    }


}

