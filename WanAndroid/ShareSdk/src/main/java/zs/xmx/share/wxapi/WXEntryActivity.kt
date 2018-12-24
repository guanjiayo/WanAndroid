package zs.xmx.share.wxapi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.tencent.mm.opensdk.openapi.WXAPIFactory


/**
 * 微信移动端结果处理
 *
 * WXEntryActivity
 *
 * todo 到时把库用到公司项目,然后测试
 * todo finish()时加上取消过度动画
 * todo 看情况将该界面设置成透明主题
 *
 */
class WXEntryActivity : AppCompatActivity(), IWXAPIEventHandler {

    private var api: IWXAPI? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    private fun initData() {
        api = WXAPIFactory.createWXAPI(this, "wxa725306b95f9b282")
        api!!.handleIntent(intent, this)
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        api!!.handleIntent(intent, this)
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    override fun onReq(req: BaseReq) {


    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    override fun onResp(baseResp: BaseResp) {
        if (baseResp.type == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (baseResp.errCode == 0) {
                Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show()
            }
            finish()
        }

        //登录回调
        when (baseResp.errCode) {
            BaseResp.ErrCode.ERR_OK -> {
                val code = (baseResp as SendAuth.Resp).code
                //获取AccessToken
                getAccessToken(code)
            }
            BaseResp.ErrCode.ERR_AUTH_DENIED//用户拒绝授权
            -> {
                Toast.makeText(this, "用户拒绝授权", Toast.LENGTH_LONG).show()
                finish()
            }
            BaseResp.ErrCode.ERR_USER_CANCEL//用户取消
            -> {
                Toast.makeText(this, "用户取消", Toast.LENGTH_LONG).show()
                finish()
            }
            BaseResp.ErrCode.ERR_SENT_FAILED -> {//发送失败
            }
            BaseResp.ErrCode.ERR_UNSUPPORT -> {//不支持错误
            }
            BaseResp.ErrCode.ERR_COMM -> {  //一般错误
            }
            else -> finish()
        }


    }

    /**
     * 获取AccessToken
     */
    private fun getAccessToken(code: String) {
        //get请求  https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        //获取授权
        val loginUrl = StringBuffer()
        loginUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token")
                .append("?appid=")
                //.append(Constant.APP_ID) APP_ID
                .append("&secret=")
                //.append(Constant.SECRET) APP_SECRET
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code")

//todo
//        HttpRequestPresenter.getInstance().get(loginUrl.toString(), null, object : ICallback {
//            override fun onSuccess(result: String) {
//                try {
//                    val jsonObject = JSONObject(result)
//                    val accessToken = jsonObject.optString("access_token")
//                    val openid = jsonObject.optString("openid")
//                    //获取用户信息
//                    getUserInfo(accessToken, openid)
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//
//            }
//
//            override fun onFailure(errorCode: Int, t: Throwable) {
//
//            }
//        })

    }

    /**
     * 获取用户信息
     *
     * @param access_token
     * @param openid
     */
    private fun getUserInfo(access_token: String, openid: String) {
        //get请求  https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
        val userInfo_url = "https://api.weixin.qq.com/sns/userinfo?access_token=$access_token&openid=$openid"
//        HttpRequestPresenter.getInstance().get(userInfo_url, null, object : ICallback {
//            override fun onSuccess(result: String) = try {
//                //todo 具体服务器端后再看下怎么处理
//                val iWxLoginCallback = WeChatRequest.getInstance().iWxLoginCallback
//                iWxLoginCallback.onSignInSuccess(result)
//                val jsonObject = JSONObject(result)
//                //昵称
//                val nickname = jsonObject.optString("nickname")
//                //性别
//                val sex = jsonObject.optString("sex")
//                //省份
//                val province = jsonObject.optString("province")
//                //城市
//                val city = jsonObject.optString("city")
//                //国家
//                val country = jsonObject.optString("country")
//                //头像
//                val headimgurl = jsonObject.optString("headimgurl")
//                //用户唯一标识
//                val unionid = jsonObject.optString("unionid")
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
//
//            override fun onFailure(errorCode: Int, t: Throwable) {
//
//            }
//        })
    }


}