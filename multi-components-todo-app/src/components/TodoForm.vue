<template>
  <div class="mb30">
    <label>
      <input v-model="item"
             class="todo-input"
             placeholder="请输入待办事项"
             @keyup.enter="add"/>
    </label>
  </div>
</template>

<script>
export default {
  props: ['addItem'],
  data() {
    return {item: ''}
  },
  computed: {
    newItem() {
      return {
        id: this.items.length + 1,
        title: this.item,
        done: false,
        editable: false
      }
    }
  },
  methods: {
    add() {
      this.addItem(this.newItem)
    }
  }
}
</script>

<style scoped>
@import '../assets/styles.css';
</style>
