package test;

import Mundo.Cine;
import Mundo.Reserva;
import Mundo.Silla;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Reserva estén correctamente implementados
 */
public class ReservaTest extends TestCase
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Reserva reserva;

    /**
     * Cine para obtener las sillas
     */
    private Cine cine;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva Cine vacía
     */
    private void setupEscenario1( )
    {
        reserva = new Reserva( );
        cine = new Cine( );
    }

    /**
     * Prueba Silla Reservada
     */
    public void testReservarSilla( )
    {
        setupEscenario1( );

        try
        {
            //Busca la silla A1 y la agrega a la reserva
            Silla silla = cine.darSilla( 'A', 1 );
            reserva.agregarSilla( silla );
            assertEquals( "La silla no está reservada.", true, silla.estaReservada( ) );
            assertEquals( "La reserva no tiene el valor correcto", silla.darCosto( ), reserva.darSaldoReserva( ) );
        }
        catch( Exception e )
        {
            fail( "Error al agregar la silla a la reserva" );
        }
    }

    /**
     * Prueba Doble Reserva
     */
    public void testDobleReserva( )
    {
        setupEscenario1( );
        Silla silla = null;
        try
        {
            //Busca la silla A1 y la agrega a la reserva
            silla = cine.darSilla( 'A', 1 );
            reserva.agregarSilla( silla );
            assertEquals( "La silla no está reservada.", true, silla.estaReservada( ) );

            try
            {
                //reserva la silla otra vez
                reserva.agregarSilla( silla );
                fail( "La silla no se debe poder reservar 2 veces" );
            }
            catch( Exception e1 )
            {
                assertTrue( "Esta excepción era esperada", true );
            }
        }
        catch( Exception e )
        {
            fail( "Error al agregar la silla a la reserva" );
        }
    }

    /**
     * Prueba Cancelar Reserva
     */
    public void testCancelar( )
    {
        setupEscenario1( );
        try
        {
            //Busca la silla A1 y la agrega a la reserva
            Silla silla = cine.darSilla( 'A', 1 );
            reserva.agregarSilla( silla );

            //cancela la reserva
            reserva.cancelar( );
            assertEquals( "La silla está reservada.", true, silla.estaDisponible( ) );
            assertEquals( "La reserva no tiene el valor correcto", 0, reserva.darSaldoReserva( ) );
        }
        catch( Exception e )
        {
            fail( "Error al agregar la silla a la reserva" );
        }
    }
}
