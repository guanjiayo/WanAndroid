package zs.xmx.user.constant

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/8/27 0:12
 * @本类描述	  用户信息常量类
 * @内容说明
 *
 */
class UserConstant {

    companion object {

        //登录
        const val URL_LOGIN = "user/login"
        //注册
        const val URL_REGISTER = "user/register"
        //注销
        const val URL_LOGOUT = "user/logout/json"

        //用户名称
        const val KEY_SP_USER_NAME = "sp_user_name"
        //用户电话
        const val KEY_SP_USER_MOBILE = "sp_user_mobile"
        //用户头像
        const val KEY_SP_USER_ICON = "sp_user_icon"
        //用户性别
        const val KEY_SP_USER_GENDER = "sp_user_gender"
        //用户签名
        const val KEY_SP_USER_SIGN = "sp_user_sign"

        //订单ID
        const val KEY_ORDER_ID = "order_id"
        //订单价格
        const val KEY_ORDER_PRICE = "order_price"

    }


}
