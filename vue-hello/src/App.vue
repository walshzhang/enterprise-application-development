<template>
  <div>
    <h4>Counter: {{ counter }}</h4>
    <button v-on:click="increase">点一次加1</button>
  </div>
</template>
<script>
export default {
  data() {
    return ({
      counter: 0
    })
  },
  methods: {
    increase() {
      this.counter++;
    }
  }
}
</script>
