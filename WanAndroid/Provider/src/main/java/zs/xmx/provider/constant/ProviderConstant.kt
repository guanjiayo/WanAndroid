package zs.xmx.provider.constant

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @本类描述	  Provider模块的常量类
 * @内容说明
 *
 */
class ProviderConstant {

    /**
     * 模块间可能公用的属性常量
     */
    companion object {
        //订单ID
        const val KEY_ORDER_ID = "order_id"
        //订单价格
        const val KEY_ORDER_PRICE = "order_price"

        //Bus需要的TAG常量
        const val KEY_MESSAGE_TAG = "message_tag"

        //---------------------订单的支付状态-------------------------------

        const val KEY_ORDER_STATUS = "order_status"
        const val ORDER_ALL = 0//全部
        const val ORDER_WAIT_PAY = 1//待支付
        const val ORDER_WAIT_CONFIRM = 2//待收货
        const val ORDER_COMPLETED = 3//已完成
        const val ORDER_CANCELED = 4//已取消

    }


}