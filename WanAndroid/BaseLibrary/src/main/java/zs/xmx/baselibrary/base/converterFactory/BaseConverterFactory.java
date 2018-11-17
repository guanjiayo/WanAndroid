package zs.xmx.baselibrary.base.converterFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/31 17:47
 * @本类描述	  ${TODO}
 * @内容说明   ${TODO}
 *
 */
public class BaseConverterFactory extends Converter.Factory {
    public static BaseConverterFactory create() {
        return new BaseConverterFactory();
    }

    private BaseConverterFactory() {
    }

    @Override public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                                    Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if (type == String.class
                || type == boolean.class
                || type == Boolean.class
                || type == byte.class
                || type == Byte.class
                || type == char.class
                || type == Character.class
                || type == double.class
                || type == Double.class
                || type == float.class
                || type == Float.class
                || type == int.class
                || type == Integer.class
                || type == long.class
                || type == Long.class
                || type == short.class
                || type == Short.class) {
            return BaseConverter.INSTANCE;
        }
        return null;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        if (type == String.class) {
            return BaseResponseBodyConverters.StringResponseBodyConverter.INSTANCE;
        }
        if (type == Boolean.class || type == boolean.class) {
            return BaseResponseBodyConverters.BooleanResponseBodyConverter.INSTANCE;
        }
        if (type == Byte.class || type == byte.class) {
            return BaseResponseBodyConverters.ByteResponseBodyConverter.INSTANCE;
        }
        if (type == Character.class || type == char.class) {
            return BaseResponseBodyConverters.CharacterResponseBodyConverter.INSTANCE;
        }
        if (type == Double.class || type == double.class) {
            return BaseResponseBodyConverters.DoubleResponseBodyConverter.INSTANCE;
        }
        if (type == Float.class || type == float.class) {
            return BaseResponseBodyConverters.FloatResponseBodyConverter.INSTANCE;
        }
        if (type == Integer.class || type == int.class) {
            return BaseResponseBodyConverters.IntegerResponseBodyConverter.INSTANCE;
        }
        if (type == Long.class || type == long.class) {
            return BaseResponseBodyConverters.LongResponseBodyConverter.INSTANCE;
        }
        if (type == Short.class || type == short.class) {
            return BaseResponseBodyConverters.ShortResponseBodyConverter.INSTANCE;
        }
        return null;
    }
}
