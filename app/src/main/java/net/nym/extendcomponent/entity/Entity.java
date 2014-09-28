package net.nym.extendcomponent.entity;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**类Entity
 * <p>父类
 * @author nym
 * @version 2013-6-20
 * @see Entities
 * @time 下午6:17:30
 */
public abstract class Entity  {


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("{");
        Class clazz = getClass();
        string(sb, clazz);

        if (sb.length() > 1)
            sb.deleteCharAt(sb.length() - 1);
        sb.append("}");

        return sb.toString();
    }

    private void string(StringBuffer sb, Class clazz) {
        if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
            string(sb,clazz.getSuperclass());
        }
        try {
            for (Field item : clazz.getDeclaredFields()) {
                //不要static修饰的属性
                if (Modifier.toString(item.getModifiers()).indexOf("static") == -1)
                {
                    boolean accessFlag = item.isAccessible();
                    /**
                     * 设置是否有权限访问反射类中的私有属性的
                     * */
                    item.setAccessible(true);
                    sb.append(item.getName()).append(":").append(item.get(this) + "").append(",");
                    item.setAccessible(accessFlag);

                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
