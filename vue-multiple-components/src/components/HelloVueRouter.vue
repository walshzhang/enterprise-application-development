<template>
  <div>
    <div id="navbar">
      <!-- 使用 router-link 组件来导航 -->
      <!-- 使用 to 属性指定去链接 -->
      <router-link to="/hello-router/about" style="margin-right: 5px;">关于我</router-link>
      <router-link to="/hello-router/travel">旅游日记</router-link>
    </div>
    <div style="margin-top: 10px;">
      <!-- 路由出口 -->
      <!-- 路由匹配的组件将渲染到这里 -->
      <router-view></router-view>
    </div>
  </div>
</template>
