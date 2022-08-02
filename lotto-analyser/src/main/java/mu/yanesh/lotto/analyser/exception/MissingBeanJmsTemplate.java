package mu.yanesh.lotto.analyser.exception;

public class MissingBeanJmsTemplate extends RuntimeException {

    public MissingBeanJmsTemplate(String message) {
        super(message);
    }

    public MissingBeanJmsTemplate() {
        super();
    }
}
