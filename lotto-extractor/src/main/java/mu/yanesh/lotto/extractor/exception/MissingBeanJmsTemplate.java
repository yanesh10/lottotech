package mu.yanesh.lotto.extractor.exception;

public class MissingBeanJmsTemplate extends RuntimeException {

    public MissingBeanJmsTemplate(String message) {
        super(message);
    }

    public MissingBeanJmsTemplate() {
        super();
    }
}
