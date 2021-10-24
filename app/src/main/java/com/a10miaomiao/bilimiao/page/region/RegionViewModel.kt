package com.a10miaomiao.bilimiao.page.region

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.a10miaomiao.bilimiao.MainNavGraph
import com.a10miaomiao.bilimiao.comm.entity.region.RegionInfo
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

class RegionViewModel(
    override val di: DI,
) : ViewModel(), DIAware {

    val fragment: Fragment by instance()

    val region = fragment.requireArguments().getParcelable<RegionInfo>(MainNavGraph.args.region)!!
    val fragments = Array<Fragment?>(region.children.size) { null }

    init {

    }

}