package chat.Utils;

import java.util.ArrayList;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;

public class LogUtils {
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("before:" + joinPoint.getSignature().getName() + "\nparam:" + Arrays.asList(joinPoint.getArgs()));
	}
	
	public void logAfter(JoinPoint joinPoint) {
		System.out.println("after:" + joinPoint.getSignature().getName() + "\nparam:" + Arrays.asList(joinPoint.getArgs()));
	}
	
	public void logAfterThrowing(JoinPoint joinPoint) {
		System.out.println("after-throwing:" + joinPoint.getSignature().getName() + "\nparam:" + Arrays.asList(joinPoint.getArgs()));
	}
	
	public void logAfterReturn(JoinPoint joinPoint) {
		System.out.println("after-return:" + joinPoint.getSignature().getName() + "\nparam:" + Arrays.asList(joinPoint.getArgs()));
	}
}
