package com.yunzia.hyperstar.ui.screen.module.systemui.volume

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.yunzia.hyperstar.R
import com.yunzia.hyperstar.ui.component.ContentFolder
import com.yunzia.hyperstar.ui.component.XMiuixSuperSliderSwitch
import com.yunzia.hyperstar.ui.component.XSuperDropdown
import com.yunzia.hyperstar.ui.component.XSuperSliders
import com.yunzia.hyperstar.ui.component.XSuperSwitch
import com.yunzia.hyperstar.ui.component.classes
import com.yunzia.hyperstar.ui.component.firstClasses
import com.yunzia.hyperstar.ui.component.pager.ModuleNavPagers
import com.yunzia.hyperstar.utils.Helper
import com.yunzia.hyperstar.utils.isOS2Settings

@Composable
fun VolumeScreen(
    navController: NavController,
    currentStartDestination: MutableState<String>
) {
    ModuleNavPagers(
        activityTitle = stringResource(R.string.sound_settings),
        navController = navController,
        parentRoute = currentStartDestination,
        endClick = {
            Helper.rootShell("killall com.android.systemui")
                   },
    ){
        firstClasses(
            title = R.string.basics
        ) {
            XSuperDropdown(
                title = stringResource(R.string.is_super_blur_volume_title),
                key = "is_super_blur_volume",
                option = R.array.is_super_blur_entire
            )

            if (!isOS2Settings()){
                XSuperSwitch(
                    title = stringResource(R.string.title_use_pad_volume),
                    summary = stringResource(R.string.summary_use_pad_volume),
                    key = "is_use_pad_volume"
                )
            }
            if (isOS2Settings()){
                XSuperSwitch(
                    title = stringResource(R.string.title_volume_top_value_show),
                    key = "volume_top_value_show"
                )
            }
            XMiuixSuperSliderSwitch(
                switchTitle = stringResource(R.string.is_change_qs_progress_radius_title),
                switchKey = "is_change_volume_progress_radius",
                switchSummary = stringResource(id = R.string.progress_radius_summary),
                title = stringResource(R.string.qs_progress_radius_title) ,
                key ="volume_progress_radius",
                minValue = 0f,
                maxValue = 20f,
                progress = 2f,
                unit = "dp",
                decimalPlaces = 1
            )
        }
        classes(title = R.string.sidebar_mode){


            if (isOS2Settings()){
                XSuperSwitch(
                    title = stringResource(R.string.title_press_expand_volume),
                    summary = stringResource(R.string.summary_press_expand_volume),
                    key = "is_press_expand_volume"
                )

            }
            XSuperSwitch(
                title = stringResource(R.string.title_standardview_hide),
                key = "is_hide_StandardView"
            )
            OrientationDimBarFolder(
                stringResource(R.string.title_volume_height_collapsed),"volume_height_collapsed",300f
            )
            OrientationDimBarFolder(
                stringResource(R.string.title_volume_offset_top_collapsed),"volume_offset_top_collapsed",250f
            )
            OrientationDimBarFolder(
                stringResource(R.string.title_volume_shadow_height_collapsed),"volume_shadow_height_collapsed",300f
            )
            OrientationDimBarFolder(
                stringResource(R.string.title_volume_shadow_margin_top_collapsed),"volume_shadow_margin_top_collapsed",450f
            )

        }
    }

}


@Composable
fun OrientationDimBarFolder(
    title:String,
    key:String,
    maxValue: Float
){
    ContentFolder(title){

        XSuperSliders(
            host = title,
            title = stringResource(R.string.PORTRAIT),
            key = key+"_p",
            defValue = -1f,
            maxValue = maxValue,
            minValue = 0f,
            unit = "dp",
            decimalPlaces = 1
        )


        XSuperSliders(
            host = title,
            title = stringResource(R.string.LANDSCAPE),
            key = key+"_l",
            defValue = -1f,
            maxValue = maxValue,
            minValue = 0f,
            unit = "dp",
            decimalPlaces = 1
        )

    }
}
