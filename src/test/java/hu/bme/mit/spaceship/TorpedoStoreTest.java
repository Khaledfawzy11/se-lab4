package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success_DecreasesTorpedoCount() {
        TorpedoStore store = new TorpedoStore(2, 0.0);

        assertTrue(store.fire(1));
        assertEquals(1, store.getTorpedoCount());
    }

    @Test
    void fire_Failure_LeavesTorpedoCountUnchanged() {
        TorpedoStore store = new TorpedoStore(2, 1.0);

        assertFalse(store.fire(1));
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_ZeroTorpedoes_ThrowsException() {
        TorpedoStore store = new TorpedoStore(1, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
        assertEquals(1, store.getTorpedoCount());
    }

    @Test
    void fire_MoreTorpedoesThanAvailable_ThrowsException() {
        TorpedoStore store = new TorpedoStore(1, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(2));
        assertEquals(1, store.getTorpedoCount());
    }

    @Test
    void isEmpty_ReflectsTorpedoCount() {
        TorpedoStore emptyStore = new TorpedoStore(0, 0.0);
        assertTrue(emptyStore.isEmpty());

        TorpedoStore stockedStore = new TorpedoStore(1, 0.0);
        assertFalse(stockedStore.isEmpty());

        assertTrue(stockedStore.fire(1));
        assertTrue(stockedStore.isEmpty());
    }
}