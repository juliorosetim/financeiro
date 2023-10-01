<template>
  <div>
    <button
      class="middle none center rounded-lg bg-verde-50 py-3 px-6 font-sans text-xs font-bold uppercase text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
      data-ripple-light="true"
    >
      {{ props.text }}
    </button>
  </div>
</template>


<script setup lang="ts">
const props = defineProps({
  text: {
    required: true,
    type: String,
    default: "",
  },
});
</script>
