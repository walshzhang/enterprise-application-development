<template>
  <div class="app-center">
    <todo-form add-item="addItem"/>
    <todo-list data="itemsInTable"
               update-item="updateItem"
               remove-item="removeItem"/>
    <todo-mode modes="modes" change-mode="mode => changeMode(mode)"/>
  </div>
</template>

<script>
import TodoForm from './components/TodoForm'
import TodoList from './components/TodoList'
import TodoMode from "./components/TodoMode";

export default {
  components: {TodoList, TodoForm, TodoMode},
  data() {
    return {
      items: [],
      modes: [
        {value: 'all', text: '查看所有'},
        {value: 'undone', text: '查看未完成'},
        {value: 'done', text: '查看完成'}
      ],
      mode: 'all'
    }
  },
  computed: {
    itemsInTable() {
      switch (this.mode) {
        case 'all':
          return this.items;
        case 'undone':
          return this.items.filter(item => !item.done)
        default:
          return this.items.filter(item => item.done)
      }
    }
  },
  created() {
    this.items = JSON.parse(localStorage.getItem('todos')) || []
  },
  methods: {
    addItem(item) {
      this.items.push(item)
      localStorage.setItem('todos', JSON.stringify(this.items))
    },
    removeItem(id) {
      this.items = this.items.filter(item => item.id !== id)
      localStorage.setItem('todos', JSON.stringify(this.items))
    },
    updateItem(id) {
      this.items = this.items.map(
          item => item.id === id
              ? {...item, editable: false}
              : item)
      localStorage.setItem('todos', JSON.stringify(this.items))
    },
    changeMode(mode) {
      this.mode = mode
    }
  }
}
</script>

<style scoped>
@import 'assets/styles.css';
</style>
