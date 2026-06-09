package com.zycg.stop.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.zycg.stop.model.ObservationItems
import com.zycg.stop.model.StopForm
import com.zycg.stop.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var formState by remember { mutableStateOf(StopForm()) }
    var showSubmitDialog by remember { mutableStateOf(false) }
    var showResetDialog by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("中盐常化STOP填报系统", style = MaterialTheme.typography.titleMedium)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue700,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { showResetDialog = true }) {
                        Icon(Icons.Filled.Refresh, contentDescription = "重置")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 页眉口号
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Blue50)
            ) {
                Text(
                    text = "鼓励安全行为、增强安全意识、营造安全氛围",
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Blue700,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // ========== 基本信息区 ==========
            SectionTitle(title = "基本信息")

            OutlinedTextField(
                value = formState.observedWork,
                onValueChange = { formState = formState.copy(observedWork = it) },
                label = { Text("被观察的作业或行为 *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = outlinedFieldColors()
            )

            OutlinedTextField(
                value = formState.observationDate,
                onValueChange = { formState = formState.copy(observationDate = it) },
                label = { Text("日期 *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("如：2026-06-08") },
                trailingIcon = { Icon(Icons.Filled.CalendarToday, contentDescription = null) },
                colors = outlinedFieldColors()
            )

            OutlinedTextField(
                value = formState.areaFacility,
                onValueChange = { formState = formState.copy(areaFacility = it) },
                label = { Text("区域/设施 *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = outlinedFieldColors()
            )

            OutlinedTextField(
                value = formState.observedDepartment,
                onValueChange = { formState = formState.copy(observedDepartment = it) },
                label = { Text("被观察的单位/部门 *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = outlinedFieldColors()
            )

            // 是否属地
            Text("是否本班组/部门责任属地", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
            Row(modifier = Modifier.selectableGroup()) {
                RadioButtonRow(
                    label = "是",
                    selected = formState.isOwnArea == true,
                    onClick = { formState = formState.copy(isOwnArea = true) }
                )
                Spacer(modifier = Modifier.width(24.dp))
                RadioButtonRow(
                    label = "否",
                    selected = formState.isOwnArea == false,
                    onClick = { formState = formState.copy(isOwnArea = false) }
                )
            }

            // ========== 情况类别 ==========
            SectionTitle(title = "情况类别（可多选）")
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Grey100)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    CheckboxRow(
                        label = "不安全行为",
                        checked = formState.hasUnsafeBehavior,
                        onCheckedChange = { formState = formState.copy(hasUnsafeBehavior = it) }
                    )
                    CheckboxRow(
                        label = "不安全状态",
                        checked = formState.hasUnsafeCondition,
                        onCheckedChange = { formState = formState.copy(hasUnsafeCondition = it) }
                    )
                    CheckboxRow(
                        label = "未遂事件（虚惊事件）",
                        checked = formState.hasNearMiss,
                        onCheckedChange = { formState = formState.copy(hasNearMiss = it) }
                    )
                    CheckboxRow(
                        label = "推荐的安全行为",
                        checked = formState.hasSafeBehavior,
                        onCheckedChange = { formState = formState.copy(hasSafeBehavior = it) }
                    )
                }
            }

            // ========== 描述与措施 ==========
            SectionTitle(title = "情况描述与措施")

            OutlinedTextField(
                value = formState.description,
                onValueChange = { formState = formState.copy(description = it) },
                label = { Text("情况描述") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 6,
                colors = outlinedFieldColors()
            )

            OutlinedTextField(
                value = formState.improvementMeasures,
                onValueChange = { formState = formState.copy(improvementMeasures = it) },
                label = { Text("措施及改进建议") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 6,
                colors = outlinedFieldColors()
            )

            // ========== 统计数据 ==========
            SectionTitle(title = "统计数据")
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = formState.observedCount,
                    onValueChange = { formState = formState.copy(observedCount = it) },
                    label = { Text("被观察人数") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = outlinedFieldColors()
                )
                OutlinedTextField(
                    value = formState.unsafeBehaviorCount,
                    onValueChange = { formState = formState.copy(unsafeBehaviorCount = it) },
                    label = { Text("不安全行为人数") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = outlinedFieldColors()
                )
                OutlinedTextField(
                    value = formState.unsafeIssueCount,
                    onValueChange = { formState = formState.copy(unsafeIssueCount = it) },
                    label = { Text("不安全问题数") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = outlinedFieldColors()
                )
            }

            // ========== 五步观察法 ==========
            SectionTitle(title = "五步观察法：观察 → 表扬 → 讨论 → 沟通 → 启发 → 感谢")

            // RP - 员工的反应
            ObservationSection(
                title = ObservationItems.STEP_TITLES[0],
                options = ObservationItems.RP_OPTIONS,
                selections = formState.rpSelections,
                otherText = formState.rpOther,
                onSelectionChange = { formState = formState.copy(rpSelections = it) },
                onOtherChange = { formState = formState.copy(rpOther = it) }
            )

            // PP - 员工的位置
            ObservationSection(
                title = ObservationItems.STEP_TITLES[1],
                options = ObservationItems.PP_OPTIONS,
                selections = formState.ppSelections,
                otherText = formState.ppOther,
                onSelectionChange = { formState = formState.copy(ppSelections = it) },
                onOtherChange = { formState = formState.copy(ppOther = it) }
            )

            // PPE - 个人防护用品
            ObservationSection(
                title = ObservationItems.STEP_TITLES[2],
                options = ObservationItems.PPE_OPTIONS,
                selections = formState.ppeSelections,
                otherText = formState.ppeOther,
                onSelectionChange = { formState = formState.copy(ppeSelections = it) },
                onOtherChange = { formState = formState.copy(ppeOther = it) }
            )

            // TE - 工具和设备
            ObservationSection(
                title = ObservationItems.STEP_TITLES[3],
                options = ObservationItems.TE_OPTIONS,
                selections = formState.teSelections,
                otherText = formState.teOther,
                onSelectionChange = { formState = formState.copy(teSelections = it) },
                onOtherChange = { formState = formState.copy(teOther = it) }
            )

            // PS&HO - 程序与现场环境
            ObservationSection(
                title = ObservationItems.STEP_TITLES[4],
                options = ObservationItems.PSHO_OPTIONS,
                selections = formState.pshoSelections,
                otherText = formState.pshoOther,
                onSelectionChange = { formState = formState.copy(pshoSelections = it) },
                onOtherChange = { formState = formState.copy(pshoOther = it) }
            )

            // ========== 报告人信息 ==========
            SectionTitle(title = "报告人信息")

            OutlinedTextField(
                value = formState.reporter,
                onValueChange = { formState = formState.copy(reporter = it) },
                label = { Text("报告人 *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = outlinedFieldColors()
            )

            OutlinedTextField(
                value = formState.reporterDepartment,
                onValueChange = { formState = formState.copy(reporterDepartment = it) },
                label = { Text("单位/部门 *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = outlinedFieldColors()
            )

            // ========== 提交按钮 ==========
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { showSubmitDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Blue700),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Icon(Icons.Filled.CheckCircle, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("提交记录", style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }

    // 提交确认对话框
    if (showSubmitDialog) {
        AlertDialog(
            onDismissRequest = { showSubmitDialog = false },
            title = { Text("确认提交") },
            text = {
                Text("请核对填写内容，提交后将保存本记录。\n\n报告人：${formState.reporter.ifEmpty { "未填写" }}\n被观察单位：${formState.observedDepartment.ifEmpty { "未填写" }}")
            },
            confirmButton = {
                Button(
                    onClick = { showSubmitDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = Blue700)
                ) {
                    Text("确认提交")
                }
            },
            dismissButton = {
                TextButton(onClick = { showSubmitDialog = false }) {
                    Text("返回修改")
                }
            }
        )
    }

    // 重置确认对话框
    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text("确认重置") },
            text = { Text("将清空所有已填写内容，此操作不可撤销。") },
            confirmButton = {
                Button(
                    onClick = {
                        formState = StopForm()
                        showResetDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Red500)
                ) {
                    Text("确认重置")
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("取消")
                }
            }
        )
    }
}

@Composable
fun SectionTitle(title: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Blue50)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Icon(
            Icons.Filled.Shield,
            contentDescription = null,
            tint = Blue700,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Blue700,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun RadioButtonRow(label: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .selectable(
                selected = selected,
                onClick = onClick,
                role = Role.RadioButton
            )
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = null)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun CheckboxRow(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = checked,
                onClick = { onCheckedChange(!checked) },
                role = Role.Checkbox
            )
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = checked, onCheckedChange = null)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun ObservationSection(
    title: String,
    options: List<String>,
    selections: Set<String>,
    otherText: String,
    onSelectionChange: (Set<String>) -> Unit,
    onOtherChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column {
            // 可点击标题栏
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = expanded,
                        onClick = { expanded = !expanded }
                    ),
                color = Grey100
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = if (selections.isEmpty()) "未选择" else "已选 ${selections.size} 项",
                        style = MaterialTheme.typography.labelSmall,
                        color = if (selections.isEmpty()) Grey500 else Blue700
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = null,
                        tint = Grey700
                    )
                }
            }

            // 展开的选项列表
            if (expanded) {
                Divider(color = Grey200)
                Column(modifier = Modifier.padding(12.dp)) {
                    options.forEach { option ->
                        val isOther = option == "其他"
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = selections.contains(option),
                                    onClick = {
                                        val newSet = if (selections.contains(option)) {
                                            selections - option
                                        } else {
                                            selections + option
                                        }
                                        onSelectionChange(newSet)
                                    },
                                    role = Role.Checkbox
                                )
                                .padding(vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = selections.contains(option),
                                onCheckedChange = null
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = option,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        // 选中"其他"时显示补充文本框
                        if (isOther && selections.contains("其他")) {
                            Spacer(modifier = Modifier.height(4.dp))
                            OutlinedTextField(
                                value = otherText,
                                onValueChange = onOtherChange,
                                label = { Text("请补充说明") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 40.dp),
                                singleLine = true,
                                colors = outlinedFieldColors()
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun outlinedFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = Blue500,
    unfocusedBorderColor = Grey500,
    focusedLabelColor = Blue700,
    cursorColor = Blue500
)
