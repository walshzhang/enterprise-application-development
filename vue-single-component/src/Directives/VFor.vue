<template>
  <div>
    <h2>待办事项</h2>
    <ul>
      <li v-for="task in tasks" :key="task.id">
        {{ task.title }}
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  data() {
    return ({
      tasks: [
        {id: 1, title: '学习 Vue 指令'},
        {id: 2, title: '打一局游戏'},
        {id: 3, title: '操场上跑五圈'}
      ]
    });
  }
}
</script>
