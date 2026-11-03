package com.richi_mc.proyectodamn

import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.richi_mc.proyectodamn.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // La barra superior es oscura en ambos temas, así que los íconos de la barra de estado van claros.
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        // La barra superior toma el inset de arriba (fitsSystemWindows); aquí se reserva el de abajo,
        // que también cubre el teclado para que no tape los formularios.
        ViewCompat.setOnApplyWindowInsetsListener(binding.contenido) { v, insets ->
            val barras = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime()
            )
            v.setPadding(barras.left, 0, barras.right, barras.bottom)
            insets
        }

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController

        // Todas las pantallas del menú son de primer nivel: siempre se muestra el ícono de hamburguesa.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.inicioFragment,
                R.id.anadirEventoFragment,
                R.id.consultarFragment,
                R.id.calendarioFragment,
                R.id.respaldoFragment,
                R.id.restaurarFragment,
                R.id.configuracionFragment,
                R.id.acercaDeFragment,
            ),
            binding.drawerLayout,
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
        binding.bottomNav.setupWithNavController(navController)

        // "Salir" no es un destino del grafo: cierra la app.
        binding.navView.menu.findItem(R.id.salir).setOnMenuItemClickListener { finish(); true }
        binding.bottomNav.menu.findItem(R.id.salir).setOnMenuItemClickListener { finish(); true }
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
}
