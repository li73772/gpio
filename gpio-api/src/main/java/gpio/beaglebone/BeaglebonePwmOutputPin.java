package gpio.beaglebone;

import gpio.BeagleboneGpioDevice;
import gpio.GpioDevice;
import gpio.PinDefinition;
import gpio.PwmOutputPin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;

/**
 * Output PWM pin.
 * @author Koert Zeilstra
 */
public class BeaglebonePwmOutputPin implements PwmOutputPin {
    private PinDefinition pinDefinition;
    private BeagleboneGpioDevice device;
    private OutputStreamWriter period;
    private OutputStreamWriter duty;
    private OutputStreamWriter polarity;
    private long periodNs;
    private long dutyCycleNs;
    private float dutyCycle;

    /**
     * Constructor.
     * @param pinDefinition Pin.
     * @param device Device abstraction.
     * @throws java.io.IOException Failed to read/write device.
     */
    public BeaglebonePwmOutputPin(PinDefinition pinDefinition, BeagleboneGpioDevice device) throws IOException {
        //To change body of created methods use File | Settings | File Templates.
        this.pinDefinition = pinDefinition;
        this.device = device;
        device.setup(pinDefinition, GpioDevice.PinUse.OUTPUT_PWM);
        File pwmTest = device.findFile(device.getOcpDir(), "pwm_test_" + pinDefinition.getKey(), true);
        
        period = new OutputStreamWriter(new FileOutputStream(new File(pwmTest, "period")));
        duty = new OutputStreamWriter(new FileOutputStream(new File(pwmTest, "duty")));
        polarity = new OutputStreamWriter(new FileOutputStream(new File(pwmTest, "polarity")));
        dutyCycle((float) 0.0).frequency((float) 2000.0).polarity(false);
    }

    /**
     * @param frequency Frequency in Hz.
     * @throws java.io.IOException Failed to read/write device.
     */
    @Override
    public BeaglebonePwmOutputPin frequency(float frequency) throws IOException {
        if (frequency <= 0.0) {
            throw new IllegalArgumentException("frequency must be greater than 0");
        }
        this.periodNs = BigDecimal.valueOf(1e9).divide(new BigDecimal(frequency)).longValue();
        // change the duty before changing the period as the change would fail because the duty is longer than the period.
        if (this.periodNs <= this.dutyCycleNs) dutyCycle(this.dutyCycleNs);
        this.period.write(Long.toString(this.periodNs));
        if (this.periodNs > this.dutyCycleNs) dutyCycle(this.dutyCycle);
        this.period.flush();
        return this;
    }

    /**
     * @param polarity Polarity.
     * @throws java.io.IOException Failed to read/write device.
     */
    @Override
    public BeaglebonePwmOutputPin polarity(boolean polarity) throws IOException {
        if (polarity) {
            this.polarity.write("1");
        } else {
            this.polarity.write("0");
        }
        this.polarity.flush();
        return this;
    }

    /**
     * @param dutyCycle Duty cycle, minimum value is 0, maximum value is 1.
     * @throws java.io.IOException Failed to read/write device.
     */
    @Override
    public BeaglebonePwmOutputPin dutyCycle(float dutyCycle) throws IOException {
        if (dutyCycle < 0.0 || dutyCycle > 1.0) {
            new IllegalArgumentException("dutyCycle must have a value from 0.0 to 1.0");
        }
        this.dutyCycle = dutyCycle;
        this.dutyCycleNs = (long) (this.periodNs * dutyCycle);
        this.duty.write(Long.toString(this.dutyCycleNs));
//        System.out.println("dutyCycle " + periodNs + " " + dutyCycle + " " + this.dutyCycleNs);
        this.duty.flush();
        return this;
    }

    /**
     * @param dutyCycle Duty cycle, minimum value is 0, maximum value is Short.MAX_VALUE.
     * @throws java.io.IOException Failed to read/write device.
     */
    @Override
    public BeaglebonePwmOutputPin dutyCycle(short dutyCycle) throws IOException {
        if (dutyCycle < 0) {
            new IllegalArgumentException("dutyCycle must have a value from 0 to (including) Short.MAX_VALUE");
        }
        try {
        this.dutyCycle = dutyCycle / Short.MAX_VALUE;
        this.dutyCycleNs = (long) (this.periodNs * this.dutyCycle);
        this.duty.write(Long.toString(this.dutyCycleNs));
//        System.out.println("dutyCycle " + periodNs + " " + dutyCycle + " " + this.dutyCycleNs);
        this.duty.flush();
        } catch (IOException e) {
            System.out.println("IOException dutyCycle=" + dutyCycle);
            throw e;
        }
        return this;
    }

    /**
     * Stop using this pin.
     */
    @Override
    public void close() throws IOException {
        try {
            period.close();
        } catch (IOException e) {
        }
        try {
            duty.close();
        } catch (IOException e) {
        }
        try {
            polarity.close();
        } catch (IOException e) {
        }
        device.close(pinDefinition);
    }
}
