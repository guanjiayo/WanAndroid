package zs.xmx.user.ui.activity

import zs.xmx.baselibrary.base.activity.BaseActivity
import zs.xmx.baselibrary.base.web.WebFragmentImpl
import zs.xmx.user.R

class WebViewActivity : BaseActivity() {

    override fun setContentViewId(): Int {
        return R.layout.activity_web_view
    }

    override fun initView() {
        val webDelegateImpl = WebFragmentImpl.create("index.html")
        supportFragmentManager.beginTransaction()
                .replace(R.id.content_frameLayout, webDelegateImpl)
                .commit()

    }
}
