package zs.xmx.provider.router

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @本类描述	  路由模块的目标路径定义
 * @内容说明   在模块的任意Activity:
 *           //配置路由要跳转的目标Act (需要配置两级路径)
 *             @Route(path = RouterPath.UserCenter.PATH_LOGIN)
 *             //调用一次
 *             ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
 *
 *             那么整个模块的其他Activity就不需要使用,可以直接跳转了
 *
 */
object RouterPath {

    /**
     *   用户模块
     */
    class UserCenter {
        companion object {
            const val PATH_LOGIN = "/userCenter/login"
            const val PATH_ME = "/userCenter/me"
        }
    }

    /**
     *   商品模块
     */
    class GoodsCenter {
        companion object {
            const val PATH_SEARCH = "/goodsCenter/search"
            const val PATH_CART = "/goodsCenter/cart"
            const val PATH_CATEGORY = "/goodsCenter/category"
            const val PATH_GOODS_LIST = "/goodsCenter/goodsList"

            //todo 测试,要删掉
            const val PATH_GOODS_DETAIL = "/goodsCenter/goodsDetail"
        }
    }

    /**
     *  订单模块
     */
    class OrderCenter {
        companion object {
            //"确认订单"页面
            const val PATH_CONFIRM = "/orderCenter/confirm"
            //"订单状态"页面
            const val PATH_STATUS = "/orderCenter/status"
            //"收货地址"页面
            const val PATH_SHIP_ADDRESS = "/orderCenter/shipAddress"
        }
    }



    /**
     * 支付模块
     */
    class PaySDK {
        companion object {
            const val PATH_PAY = "/paySDK/pay"
        }
    }

    /**
     * 消息模块
     */
    class MessageCenter {
        companion object {
            const val PATH_MESSAGE_PUSH = "/messageCenter/push"
            const val PATH_MESSAGE_ORDER = "/messageCenter/order"
            const val PATH_MESSAGE = "/messageCenter/message"
        }
    }

}