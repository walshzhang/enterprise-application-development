<template>
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
      <tbody v-if="data.length">
      <todo-item v-for="item in data"
                 update-item="updateItem"
                 remove-item="removeItem"
                 :key="item.id"
                 item="item"/>
      </tbody>
      <tbody v-else>
      <tr>
        <td colspan="3">无数据</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import TodoItem from './TodoItem'

export default {
  props: ['data', 'updateItem', 'removeItem'],
  components: {TodoItem},
}
</script>

<style scoped>
@import '../assets/styles.css';
</style>
