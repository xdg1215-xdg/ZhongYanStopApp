# 中盐常化STOP填报系统

安全行为观察（STOP - Safety Training Observation Program）填报统计 Android App。

## 功能

基于《安全观察与沟通记录表》设计的移动端填报系统，支持：

- 基本信息录入（被观察作业、日期、区域、部门、属地归属）
- 情况类别勾选（不安全行为、不安全状态、未遂事件、推荐安全行为）
- 情况描述与改进措施填写
- 统计数据记录（被观察人数、不安全行为人数、不安全问题数）
- 五步观察法明细：员工反应(RP)、员工位置(PP)、个人防护用品(PPE)、工具设备(TE)、程序与现场环境(PS&HO)
- 报告人信息
- 表单重置

## 技术栈

- Kotlin + Jetpack Compose
- Material 3
- Min SDK 24 / Target SDK 34

## 构建

```bash
# Debug
./gradlew assembleDebug

# Release
./gradlew assembleRelease
```

## GitHub Actions 自动构建

推送代码到 `main` 或 `master` 分支即自动构建 APK，产物可在 Actions → Artifacts 下载。
