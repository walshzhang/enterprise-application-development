<template>
  <div>
    <label v-for="mode in modes" :key="mode.value">
      <input type="radio"
             v-model="mode.value"
             @click="changeMode(mode.value)"/> {{ mode.text }}
    </label>
  </div>
</template>

<script>
export default {
  props: ['modes', 'changeMode']
}
</script>

<style scoped>
@import '../assets/styles.css';
</style>
