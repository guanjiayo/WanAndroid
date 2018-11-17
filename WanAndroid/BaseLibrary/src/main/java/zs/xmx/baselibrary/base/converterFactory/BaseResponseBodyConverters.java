package zs.xmx.baselibrary.base.converterFactory;


import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/31 18:01
 * @本类描述	  仿写converter-scalars的ResponseBodyConverters
 * @内容说明  todo 用作后面做网络数据解析根类(如data字段类型会改变,或其他错误处理)
 *
 */
public class BaseResponseBodyConverters {
    private BaseResponseBodyConverters() {
    }

    static final class StringResponseBodyConverter implements Converter<ResponseBody, String> {
        static final BaseResponseBodyConverters.StringResponseBodyConverter INSTANCE = new BaseResponseBodyConverters.StringResponseBodyConverter();

        @Override
        public String convert(ResponseBody value) throws IOException {
            //{"msg":"success","result":"74442d64ca5acf2dca468f81032adac8b027cfb3","retCode":"200"}
            //{"msg":"此用户名已存在!","retCode":"22806"}

            //            JSONObject jsonObject = null;
            //
            //            try {
            //                jsonObject = new JSONObject(value.string());
            //            } catch (JSONException e) {
            //                e.printStackTrace();
            //            }
            //            Logger.e("测试", jsonObject.toString());
            //            String retCode = jsonObject.optString("retCode");
            //            String msg = jsonObject.optString("msg");
            //
            //            if (!retCode.equals("200")) {
            //                try {
            //                    throw new BaseException("dasd", "das");
            //                } catch (BaseException e) {
            //                    e.printStackTrace();
            //                }
            //            }
            return value.string();


            // return value.string();
            // return jsonObject.toString();

        }
    }

    static final class BooleanResponseBodyConverter implements Converter<ResponseBody, Boolean> {
        static final BaseResponseBodyConverters.BooleanResponseBodyConverter INSTANCE = new BaseResponseBodyConverters.BooleanResponseBodyConverter();

        @Override
        public Boolean convert(ResponseBody value) throws IOException {
            return Boolean.valueOf(value.string());
        }
    }

    static final class ByteResponseBodyConverter implements Converter<ResponseBody, Byte> {
        static final BaseResponseBodyConverters.ByteResponseBodyConverter INSTANCE = new BaseResponseBodyConverters.ByteResponseBodyConverter();

        @Override
        public Byte convert(ResponseBody value) throws IOException {
            return Byte.valueOf(value.string());
        }
    }

    static final class CharacterResponseBodyConverter implements Converter<ResponseBody, Character> {
        static final BaseResponseBodyConverters.CharacterResponseBodyConverter INSTANCE = new BaseResponseBodyConverters.CharacterResponseBodyConverter();

        @Override
        public Character convert(ResponseBody value) throws IOException {
            String body = value.string();
            if (body.length() != 1) {
                throw new IOException(
                        "Expected body of length 1 for Character conversion but was " + body.length());
            }
            return body.charAt(0);
        }
    }

    static final class DoubleResponseBodyConverter implements Converter<ResponseBody, Double> {
        static final BaseResponseBodyConverters.DoubleResponseBodyConverter INSTANCE = new BaseResponseBodyConverters.DoubleResponseBodyConverter();

        @Override
        public Double convert(ResponseBody value) throws IOException {
            return Double.valueOf(value.string());
        }
    }

    static final class FloatResponseBodyConverter implements Converter<ResponseBody, Float> {
        static final BaseResponseBodyConverters.FloatResponseBodyConverter INSTANCE = new BaseResponseBodyConverters.FloatResponseBodyConverter();

        @Override
        public Float convert(ResponseBody value) throws IOException {
            return Float.valueOf(value.string());
        }
    }

    static final class IntegerResponseBodyConverter implements Converter<ResponseBody, Integer> {
        static final BaseResponseBodyConverters.IntegerResponseBodyConverter INSTANCE = new BaseResponseBodyConverters.IntegerResponseBodyConverter();

        @Override
        public Integer convert(ResponseBody value) throws IOException {
            return Integer.valueOf(value.string());
        }
    }

    static final class LongResponseBodyConverter implements Converter<ResponseBody, Long> {
        static final BaseResponseBodyConverters.LongResponseBodyConverter INSTANCE = new BaseResponseBodyConverters.LongResponseBodyConverter();

        @Override
        public Long convert(ResponseBody value) throws IOException {
            return Long.valueOf(value.string());
        }
    }

    static final class ShortResponseBodyConverter implements Converter<ResponseBody, Short> {
        static final BaseResponseBodyConverters.ShortResponseBodyConverter INSTANCE = new BaseResponseBodyConverters.ShortResponseBodyConverter();

        @Override
        public Short convert(ResponseBody value) throws IOException {
            return Short.valueOf(value.string());
        }
    }
}
