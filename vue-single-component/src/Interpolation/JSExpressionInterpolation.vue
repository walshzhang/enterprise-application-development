<template>
  <div>
    <p>Number: {{ number + 1 }}</p>
    <p>Message: {{ ok ? 'YES' : 'NO' }}</p>
  </div>
</template>
<script>
export default {
  data() {
    return ({
      ok: true,
      number: 1,
    });
  }
}
</script>
