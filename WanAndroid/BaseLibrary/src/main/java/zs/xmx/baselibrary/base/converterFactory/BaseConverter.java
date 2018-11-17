package zs.xmx.baselibrary.base.converterFactory;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/31 17:41
 * @本类描述	  ${TODO}
 * @内容说明   ${TODO}
 *
 */
public class BaseConverter<T> implements Converter<T, RequestBody> {

    static final         BaseConverter<Object> INSTANCE   = new BaseConverter<>();
    private static final MediaType             MEDIA_TYPE = MediaType.parse("text/plain; charset=UTF-8");

    private BaseConverter() {
    }

    @Override
    public RequestBody convert(T value) throws IOException {

        return RequestBody.create(MEDIA_TYPE, String.valueOf(value));
    }


}
