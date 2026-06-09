package com.zycg.stop.model

import java.util.Date

/**
 * STOP 安全观察与沟通记录表单数据模型
 */
data class StopForm(
    // 基本信息
    var observedWork: String = "",          // 被观察的作业或行为
    var observationDate: String = "",        // 日期
    var areaFacility: String = "",           // 区域/设施
    var observedDepartment: String = "",     // 被观察的单位/部门
    var isOwnArea: Boolean? = null,          // 是否本班组/部门责任属地

    // 情况类别（可多选）
    var hasUnsafeBehavior: Boolean = false,       // 不安全行为
    var hasUnsafeCondition: Boolean = false,      // 不安全状态
    var hasNearMiss: Boolean = false,             // 未遂事件（虚惊事件）
    var hasSafeBehavior: Boolean = false,         // 推荐的安全行为

    // 描述与建议
    var description: String = "",            // 情况描述
    var improvementMeasures: String = "",    // 措施及改进建议

    // 统计数据
    var observedCount: String = "",          // 被观察人数
    var unsafeBehaviorCount: String = "",    // 不安全行为人数
    var unsafeIssueCount: String = "",       // 不安全问题数

    // 报告人信息
    var reporter: String = "",               // 报告人
    var reporterDepartment: String = "",     // 单位/部门

    // 五步观察法明细
    var rpSelections: Set<String> = emptySet(),    // 员工的反应 (RP)
    var rpOther: String = "",                      // RP-其他补充

    var ppSelections: Set<String> = emptySet(),    // 员工的位置 (PP)
    var ppOther: String = "",                      // PP-其他补充

    var ppeSelections: Set<String> = emptySet(),   // 个人防护用品 (PPE)
    var ppeOther: String = "",                     // PPE-其他补充

    var teSelections: Set<String> = emptySet(),    // 工具和设备 (TE)
    var teOther: String = "",                      // TE-其他补充

    var pshoSelections: Set<String> = emptySet(),  // 程序与现场环境 (PS&HO)
    var pshoOther: String = "",                    // PS&HO-其他补充

    // 元数据
    val submitTime: Date = Date()              // 提交时间
)

/**
 * 观察项数据定义
 */
object ObservationItems {

    // 第1项：员工的反应 (RP)
    val RP_OPTIONS = listOf(
        "调整或穿戴上个人防护装备",
        "突然改变工作位置",
        "重新安排工作",
        "停止或离开作业",
        "装上接地线",
        "进行上锁",
        "其他"
    )

    // 第2项：员工的位置 (PP)
    val PP_OPTIONS = listOf(
        "高处或临边",
        "运转设备旁",
        "起吊物下",
        "物料易喷出、挥发处",
        "作业空间狭窄或受限",
        "警戒区内",
        "接触有毒有害物质",
        "绊倒或滑倒",
        "不合理的姿势",
        "照明不良",
        "噪声",
        "其他"
    )

    // 第3项：个人防护用品 (PPE)
    val PPE_OPTIONS = listOf(
        "安全帽",
        "护目镜或面罩",
        "听力耳塞或耳罩",
        "呼吸护具",
        "防护手套",
        "工作服或防护服",
        "安全带",
        "防护鞋",
        "其他"
    )

    // 第4项：工具和设备 (TE)
    val TE_OPTIONS = listOf(
        "使用不合适的工具或设备",
        "使用方法不对",
        "所使用的工具或设备不安全",
        "其他"
    )

    // 第5项：程序与现场环境 (PS&HO)
    val PSHO_OPTIONS = listOf(
        "程序或规程没有建立",
        "程序或规程不适用",
        "程序或规程员工不知道或不理解",
        "程序或规程未被执行",
        "工作场所整洁规范",
        "材料或工具定置摆放",
        "作业合理有序",
        "其他"
    )

    val STEP_TITLES = listOf(
        "第1项：员工的反应（RP）",
        "第2项：员工的位置（PP）",
        "第3项：个人防护用品（PPE）",
        "第4项：工具和设备（TE）",
        "第5项：程序与现场环境（PS&HO）"
    )
}
