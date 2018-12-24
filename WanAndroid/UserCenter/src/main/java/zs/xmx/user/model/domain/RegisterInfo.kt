package zs.xmx.user.model.domain

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/11/2 17:17
 * @本类描述	  用户信息 实体类
 * @内容说明
 *
 */

data class RegisterInfo(val password: String = "",
                        val icon: String = "",
                        val id: Int = 0,
                        val type: Int = 0,
                        val email: String = "",
                        val token: String = "",
                        val username: String = "")
