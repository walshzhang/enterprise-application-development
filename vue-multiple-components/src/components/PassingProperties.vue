<template>
  <div>
    <todo-item :items="user.items" :name="user.name"/>
  </div>
</template>
<script>
const todoItem = {
  props: ['name', 'items'],
  template: `
    <div>
    <h3>{{ name }} 共有 {{ count }} 条待办事项</h3>
    <ul>
      <li v-for="item in items" :key="item.id"> {{ item.text }}</li>
    </ul>
    </div>`,
  computed: {
    count() {
      return this.items.length
    }
  }
}
export default {
  components: {todoItem},
  data() {
    return {
      user: {
        name: 'zhangsan',
        items: [
          {id: 1, text: '好好学习'},
          {id: 2, text: '天天向上'}
        ]
      }
    }
  }
}
</script>