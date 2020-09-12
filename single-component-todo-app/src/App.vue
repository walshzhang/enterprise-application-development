<template>
  <div class="app-center">
    <div class="mb30">
      <label><input v-model="item"
                    class="todo-input"
                    placeholder="请输入待办事项"
                    @keyup.enter="addItem"/></label>
    </div>
    <div class="mb30">
      <table class="todo-table">
        <thead>
        <tr>
          <th style="width: 30px;">
            <label><input type="checkbox"/></label>
          </th>
          <th>描述</th>
          <th style="width: 50px;">操作</th>
        </tr>
        </thead>
        <tbody v-if="itemsInTable.length">
        <tr v-for="item in itemsInTable" :key="item.id">
          <td><label><input type="checkbox"
                            v-model="item.done"/></label></td>
          <td style="width: 520px"
              @click="item.editable = true">
            <label v-if="item.editable">
              <input v-model="item.title"
                     @keyup.enter="updateItem(item.id)"
                     @focusout="updateItem(item.id)"/>
            </label>
            <span v-else>{{ item.title }}</span>
          </td>
          <td style="width: 50px">
            <button @click="removeItem(item.id)">删除</button>
          </td>
        </tr>
        </tbody>
        <tbody v-else>
        <tr>
          <td colspan="3">无数据</td>
        </tr>
        </tbody>
      </table>
    </div>
    <div>
      <label v-for="radio in radios" :key="radio.value">
        <input type="radio"
               :value="radio.value"
               v-model="viewMode"/> {{ radio.text }}
      </label>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      item: '',
      items: [],
      radios: [
        {value: 'all', text: '查看所有'},
        {value: 'undone', text: '查看未完成'},
        {value: 'done', text: '查看完成'}
      ],
      viewMode: 'all'
    }
  },
  computed: {
    newItem() {
      return {
        id: this.items.length + 1,
        title: this.item,
        done: false,
        editable: false
      }
    },
    itemsInTable() {
      switch (this.viewMode) {
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
    addItem() {
      this.items.push(this.newItem)
      this.item = ''
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
    }
  }
}
</script>

<style scoped>
.app-center {
  width: 600px;
  margin: 0 auto;
}

.todo-input {
  width: 100%;
  outline-style: none;
  border: solid 1px #ccc;
  border-radius: 4px;
  line-height: 30px;
  padding: 0 5px;
}

.todo-table {
  width: 100%;
  line-height: 30px;
  text-align: center;
  border-collapse: collapse;
  border-top: solid 1px #ccc;
}

.todo-table thead tr {
  border-bottom: solid 2px #dee2e6;
}

.todo-table tbody tr {
  border-bottom: solid 1px #dee2e6;
}

.mb30 {
  margin-bottom: 30px;
}
</style>
