<template>
  <div>
    <navbar style="margin-bottom: 20px; "/>
    <div style="border: solid gray 2px; padding: 20px; ">
      <router-view />
    </div>
  </div>
</template>
<script>
import Navbar from './components/Navbar'
export default {
  components: {
    Navbar
  }
}
</script>
