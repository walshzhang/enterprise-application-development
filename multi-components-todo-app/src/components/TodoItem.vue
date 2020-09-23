<template>
  <tr>
    <td><label>
      <input type="checkbox"
             v-model="item.done"/>
    </label></td>
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
</template>

<script>
export default {
  props: ['item', 'updateItem', 'removeItem']
}
</script>

<style scoped>
@import '../assets/styles.css';
</style>
