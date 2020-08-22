<template>
  <div>
    <button v-on:click="say('hi')" style="margin-right: 5px;">Say Hi</button>
    <button v-on:click="say('what')">Say what</button>
  </div>
</template>
<script>
export default {
  methods: {
    say(message) {
      alert(message);
    }
  }
}
</script>
