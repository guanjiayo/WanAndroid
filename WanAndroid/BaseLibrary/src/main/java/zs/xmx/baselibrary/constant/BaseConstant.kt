package zs.xmx.baselibrary.constant

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/8/27 0:12
 * @本类描述	  基本常量类
 * @内容说明   todo 后面稍微改下,分几个类写对应的常量
 *
 */
class BaseConstant {

    companion object {

        /**域名 */
        const val BASE_URL = "http://www.wanandroid.com/"

        //登录
        const val URL_LOGIN = "user/login"
        //注册
        const val URL_REGISTER = "user/register"
        //注销
        const val URL_LOGOUT = "user/logout/json"
        //分类
        const val URL_NAVI = "navi/json"


        //todo SP用户数据(改成Json)
        //SP表名
        const val TABLE_PREFS = "UserInfo"
        //Token Key
        const val KEY_SP_TOKEN = "token"
        //Cookies
        const val KEY_SP_COOKIES = "cookies"
        //用户名
        const val KEY_SP_USERNAME = "username"
        const val KEY_SP_PASSWORD = "password"
        const val KEY_SP_ICON = "icon"
        const val KEY_SP_COLLECTIDS = "collectIds"
        const val KEY_SP_CHAPTERTOPS = "chapterTops"
        const val KEY_SP_ID = "id"
        const val KEY_SP_TYPE = "type"
        const val KEY_SP_EMAIL = "email"

    }


}
