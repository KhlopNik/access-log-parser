public class MyClassEx {
    public void checkMax(int max) {
        if (max > 1024) {
            throw new RuntimeException("В найденной строке больше 1024 символов");
        }
    }
}
