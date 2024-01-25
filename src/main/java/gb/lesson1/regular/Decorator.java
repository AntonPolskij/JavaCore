package gb.lesson1.regular;
/**
 * Декоратор
 * */
public class Decorator {
    /**Метод декорирования числа
     * @param num результат вычислений, требующий декорации
     * @return отформатированная строка вывода
     */
    public static String decorate(int num) {
        return String.format("Here is your number: %d", num);
    }
}
