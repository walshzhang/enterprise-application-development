<template>
  <div>
    <a-component v-for="i in [1, 2]" :key="i"/>
  </div>
</template>

<script>
const A = {
  template: '<h1>Component A</h1>'
}
export default {
  components: {AComponent: A},
}
</script>
