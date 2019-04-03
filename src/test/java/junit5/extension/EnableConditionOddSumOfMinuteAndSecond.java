package junit5.extension;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.time.LocalTime;

public class EnableConditionOddSumOfMinuteAndSecond implements ExecutionCondition {
    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        System.out.println("Evaluate ExecutionCondition for test " + context.getDisplayName());
        LocalTime currentTime = LocalTime.now();

        int sum = currentTime.getMinute() + currentTime.getSecond();

        if ( (sum%10) == 0 ) {
            return ConditionEvaluationResult.disabled("Sum " + sum + " is odd ");
        } else {
            return ConditionEvaluationResult.enabled("Sum " + sum + " is NOT odd ");
        }
    }
}
