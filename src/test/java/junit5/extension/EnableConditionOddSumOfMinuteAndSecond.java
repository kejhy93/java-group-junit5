package junit5.extension;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.time.LocalTime;

public class EnableConditionOddSumOfMinuteAndSecond implements ExecutionCondition {
    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        LocalTime currentTime = LocalTime.now();

        int sum = currentTime.getMinute() + currentTime.getSecond();

        if ( (sum%2) == 0 ) {
            return ConditionEvaluationResult.enabled("Sum " + sum + " is odd ");
        } else {
            return ConditionEvaluationResult.disabled("Sum " + sum + " is NOT odd ");
        }
    }
}
