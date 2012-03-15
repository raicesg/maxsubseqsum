package edu.spsu.rgoodwin.maxsubseqsum

/**
 * Course:        CS4413
 * Student Name:  Raices Goodwin
 * Student ID:    000060962
 * Assignment #:  #2
 * Due Date:      10/26/2011
 *
 * Signature:     ___________________
 *
 * Score:         ___________________
 *
 * Description: Maximum Sub Sequence Sum Algorithm
 * The algorithm works by dividing the sequence into two halves.
 * Then the maximum sum subsequence is either entirely contained
 * in the left half, or entirely contained in the right half or
 * it crosses the middle and is in both halves. The Gui interface
 * provides an option to generate an sequence of any size in three
 * options (increasing, decreasing and random). It will then display
 * the results for the maximum sub sequence sum for the left, right,
 * and the entire span for the generated sequence. Finally it will
 * display the final max sum of the three.
 */

import swing._
import event._

object App extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "Maximum Subsequence Sum"

    // GUI
    val gui = new BorderPanel {
      // GUI Global Values
      var seqValue = new Array[Int](0)
      var seqAnswer = new MaxSubSum
      var i: Int = 1
      var j: Int = 5
      var rnd = new scala.util.Random
      var range = i to j

      // North Layout Global Content
      object seqSize extends TextField { columns = 5 }

      // West Layout Global Content
      val Button1 = new Button("Increasing order")
      val Button2 = new Button("Decreasing order")
      val Button3 = new Button("Random order")
      val Button4 = new Button("Reset")

      // Center Layout Global Content
      object generatedValue extends TextField { columns = 25 }
      object leftValue extends TextField { columns = 25 }
      object rightValue extends TextField { columns = 25 }
      object spanValue extends TextField { columns = 25 }
      object maxValue extends TextField { columns = 25 }

      // GUI Layout
      import BorderPanel.Position._
      layout {
        new FlowPanel {
          contents += new Label("Please enter Sequence Size ")
          contents += seqSize
        }
      } = North
      layout {
        new GridPanel(3,1) {
          contents += Button1
          contents += Button2
          contents += Button3
        }
      } = West
      layout {
        new GridPanel(11,1) {
          contents += new Label("Generated Value")
          contents += generatedValue
          contents += new Label("Maximum left half sum")
          contents += leftValue
          contents += new Label("Maximum right half sum")
          contents += rightValue
          contents += new Label("Maximum sum that spans both halves")
          contents += spanValue
          contents += new Label("The Maximum Subsequence Sum")
          contents += maxValue
          contents += Button4
          border = Swing.EmptyBorder(30, 30, 30, 30)
        }
      } = Center

      // Content listeners and reactors
      listenTo(seqSize, Button1, Button2, Button3, Button4)
      reactions += {
        // Increasing order button reactions
        case ButtonClicked(Button1) =>
          // Initialize sort array
          seqValue = new Array[Int](seqSize.text.toInt)
          for (count <- 0 until seqSize.text.toInt) {
            range = i to j
            seqValue(count) = range(rnd.nextInt(range length))
            i = seqValue(count)
            j = i + 5
          }
          // Display generated value for sequence
          generatedValue.text = seqValue.mkString("", " , ", "")
          // Display seq values
          leftValue.text = seqAnswer.maxSubSum(seqValue, 0, (seqSize.text.toInt /2) - 1).toString
          // Reset MaxSubSum Class Variable because of anomaly
          seqAnswer.maxSubSum(seqValue, 0, seqSize.text.toInt - 1)
          // Display seq values
          rightValue.text = seqAnswer.maxRightSum.toString
          spanValue.text = seqAnswer.middleSum.toString
          maxValue.text = seqAnswer.maxSubSum(seqValue, 0, seqSize.text.toInt - 1).toString

        // Decreasing order button reactions
        case ButtonClicked(Button2) =>
          // Initialize sort array
          seqValue = new Array[Int](seqSize.text.toInt)
          for (count <- 0 until seqSize.text.toInt) {
            range = i to j
            seqValue(count) = range(rnd.nextInt(range length))
            i = seqValue(count)
            j = i + 5
          }
          // Display generated value for sequence
          generatedValue.text = seqValue.reverse.mkString("", " , ", "")
          // Display seq values
          leftValue.text = seqAnswer.maxSubSum(seqValue.reverse, 0, (seqSize.text.toInt /2) - 1).toString
          // Reset MaxSubSum Class Variable because of anomaly
          seqAnswer.maxSubSum(seqValue, 0, seqSize.text.toInt - 1)
          // Display seq values
          rightValue.text = seqAnswer.maxRightSum.toString
          spanValue.text = seqAnswer.middleSum.toString
          maxValue.text = seqAnswer.maxSubSum(seqValue, 0, seqSize.text.toInt - 1).toString

        // Random order button reactions
        case ButtonClicked(Button3) =>
          // Initialize sort array
          seqValue = new Array[Int](seqSize.text.toInt)
          for (count <- 0 until seqSize.text.toInt) {
            range = -15 to 50
            seqValue(count) = range(rnd.nextInt(range length))
          }
          // Display generated value for sequence
          generatedValue.text = seqValue.mkString("", " , ", "")
          // Display seq values
          leftValue.text = seqAnswer.maxSubSum(seqValue, 0, (seqSize.text.toInt /2) - 1).toString
          // Reset MaxSubSum Class Variable because of anomaly
          seqAnswer.maxSubSum(seqValue, 0, seqSize.text.toInt - 1)
          // Display seq values
          rightValue.text = seqAnswer.maxRightSum.toString
          spanValue.text = seqAnswer.middleSum.toString
          maxValue.text = seqAnswer.maxSubSum(seqValue, 0, seqSize.text.toInt - 1).toString

        // Reset button reactions
        case ButtonClicked(Button4) =>
          // Reset Seq size
          seqSize.text = "0"
          // Reset GUI Global Values
          seqValue = new Array[Int](0)
          seqAnswer = new MaxSubSum
          i = 1
          j = 5
          rnd = new scala.util.Random
          range = i to j
          // Reset generated value for sequence
          generatedValue.text = " "
          // Reset Display seq values
          leftValue.text = " "
          rightValue.text = " "
          spanValue.text = " "
          maxValue.text = " "
      }
    }

    // Mainframe Layout
    contents = gui
  }
}