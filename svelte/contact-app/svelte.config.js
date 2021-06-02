import preprocess from 'svelte-preprocess';

/** @type {import('@sveltejs/kit').Config} */
const config = {
    // Consult https://github.com/sveltejs/svelte-preprocess
    // for more information about preprocessors
    preprocess: preprocess(),

    kit: {
        // hydrate the <div id="svelte"> element in src/app.html
        target: '#svelte',
        ssr: false,

        vite: {
            server: {
                proxy: {
                    '^/api/.*': {
                        target: 'http://localhost:8080/api',
                        changeOrigin: true,
                        rewrite: (path) => path.replace(/^\/api/, '')
                    }
                }
            }
        }
    },


};

export default config;