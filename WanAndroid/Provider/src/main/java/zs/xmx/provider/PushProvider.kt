package zs.xmx.provider

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * 跨模块接口调用 接口定义(这里用于推送消息)
 */
interface PushProvider:IProvider {
    fun getPushId():String
}
