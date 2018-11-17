package zs.xmx.baselibrary.base.rx

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/31 14:16
 * @本类描述	  默认的响应实体类
 * @内容说明   有利于统一将错误信息放在一起,减少重复代码
 *
 */
/**
 * todo 具体要看服务器响应的数据
 *
 * @reCode:响应状态码
 * @msg:响应文字消息
 * @result:具体响应业务对象
 */
data class BaseResp<out T>(
        val errorMsg: String,
        val errorCode: String,
        val data: T
)