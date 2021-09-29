package mu.yanesh.lottoextractor.exception;

public class MissingBeanJmsTemplate extends RuntimeException {

    public MissingBeanJmsTemplate(String message) {
        super(message);
    }

    public MissingBeanJmsTemplate() {
        super();
    }
}
