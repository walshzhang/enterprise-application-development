<template>
  <div>
    <textarea v-model="message" placeholder="添加多行文本" />
    <p> Message is {{ message }} </p>
  </div>
</template>
<script>
export default {
  data() {
    return { message: 'hello' };
  }
}
</script>
