<template>
  <div>
    <counter style="margin-right: 5px; "/>
    <counter />
  </div>
</template>
<script>
const counter = {
  data() {
    return {count: 0};
  },
  template: `
    <button @click="inc">我被点击了 {{ count }} 次</button>`,
  methods: {
    inc() {
      this.count++;
    }
  }
};

export default {
  components: {
    counter
  }
}
</script>
