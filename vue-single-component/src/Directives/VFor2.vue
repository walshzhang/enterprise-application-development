<template>
  <div>
    <h2>缺勤学生名单</h2>
    <ul>
      <li v-for="student of students" :key="student.name">
        {{ student.name }} 缺勤 {{ student.count }} 次
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  data() {
    return ({
      students: [
        {name: '张三', count: 5},
        {name: '李四', count: 3},
        {name: '王五', count: 0},
      ]
    });
  }
}
</script>
