package zs.xmx.baselibrary.base.global;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/6/11 0:42
 * @本类描述	  全局配置文件
 * @内容说明
 *
 */
public class Configurator {
    private static final HashMap<Object, Object>       CONFIGS      = new HashMap<>();
    private static final ArrayList<Interceptor>        INTERCEPTORS = new ArrayList<>();
    private static final ArrayList<IconFontDescriptor> ICONS        = new ArrayList<>();


    /**
     * 静态内部类单例,特别在请求网络时要处理并发问题
     **/
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private Configurator() {
        CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);
    }

    //------------上面三句,静态单例-----------

    /**
     * 获取配置信息
     **/
    final HashMap<Object, Object> getConfigs() {
        return CONFIGS;
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) CONFIGS.get(key);
    }

    /**
     * 配置API主机名(返回自己,实现链式调度)
     **/
    public final Configurator withNativeApiHost(String host) {
        CONFIGS.put(ConfigKeys.NATIVE_API_HOST, host);
        return this;
    }

    /**
     * 配置web的API主机名(返回自己,实现链式调度)
     * <p>
     * 如果和API主机名一样,就配置一样
     **/
    public final Configurator withWebApiHost(String host) {
        //只留下域名，否则无法同步cookie，不能带http://或末尾的/
        String hostName = host
                .replace("http://", "")
                .replace("https://", "");
        hostName = hostName.substring(0, hostName.lastIndexOf('/'));
        CONFIGS.put(ConfigKeys.WEB_API_HOST, hostName);
        return this;
    }

    /**
     * 字体图标库配置
     * <p>
     * 添加自定义的图标库,可以仿写FontAwesomeModule,
     * 调用: .withIcon(new FontAwesomeModule())
     */
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        //添加自定义的图标
        ICONS.add(descriptor);
        return this;
    }

    private void initIcons() {
        //默认使用第一个图标
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    
    /**
     * 设置单个拦截器
     **/
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 设置多个拦截器
     **/
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 检查配置是否完成
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure()");
        }
    }

    /**
     * 配置完成
     * <p>
     * 结尾必须要配置
     **/
    public final void configure() {
        initIcons();
        CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }
}

