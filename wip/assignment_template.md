---
geometry: margin=20mm
header-includes: |
    \usepackage{fancyhdr}
    \pagestyle{fancy}
    \lhead{Jaan Jaerving, Snorri Steinn Stefánsson Thors}
    \chead{}
    \rhead{REI504M, Assignment 2}
...

## 1: Access Data & Understand Google Collaboratory Cloud

### 1.1: Download the different Jupyter notebooks from CANVAS and load them into the Google Collaboratory Cloud. Describe in 2-3 lines how you are able to upload it and how the uploaded files connect to your Google Drive account. What are these three files as a whole describing?

In general the upload went smoothly, we simply dragged and dropped the notebooks into the provided upload box upon choosing to upload a new notebook. It automatically registered the notebooks to the Google account that was currently signed in and at that point share-links could easily be made.

\

**What are the three files as a whole describing:**

* CC_BD_Data_Understanding:
  - This file effectively shows us the shape and format of the mnist dataset to provide data understanding.
  - Initially we load the dataset into the notebook.
  - We summarize the 28x28 shape of the dataset.
  - We plot the first few images of the dataset.
* CC_BD_ANN_Model:
  - This file also loads the mnist dataset but here we begin by flattening the 28x28 matrix representation of each image down to a vector shape.
  - We then normalize the grayscale pixels down to a 0-1 range.
  - We one hot encode our Y-vector.
  - We then create and build an interconnected 2 hidden layer neural network with adam optimizing.
  - We then fit the model to our data with 10 epochs, a batch sizes of 200 and set the runtime output to be verbose.
  - We then check the accuracy by printing the baseline error percentage.
* CC_BD_CNN_Model:
  - This file mostly has function definitions for the following:
    * To load the mnist dataset and convert it to a single channel and one hot encode the Y-vector
    * To convert the image pixels to a normalized 0-1 format
    * To define a CNN model with a stochastic gradient descent optimizer and:
      - A convolution layer
      - A pooling layer
      - A flatten layer
      - Two interconnected relu and softmax layers
    * To evaluate a model with with kfold cross validation ( default 5 folds ) and keep a running history of model evaluation.
    * Two plotting functions to describe:
      - The cross entropy loss and classification accuracy over each epoch
      - The mean accuracy of the model
    * A main runner function to call all the above
  - After all the above definitions the main runner function is called and we get the outputs.

\pagebreak

### 1.2: Execute the Data Understanding script and describe for each cell below what happens in each cell via 2-3 lines.

1. This is the standard python library import cell which fetches `mnist` from the `keras`.datasets library and `pyplot` from the `matplotlib` library.
2. This uses the imported `mnist.load_data()` function to obtain the data, split into training and testing sets.
3. This prints the shape of the newly created data sets.
4. This prints the first 9 images in the training set in grayscale.
5. This shows the previously created plot.

\

### 1.3: Execute the Artificial Neural Network script and describe for each cell below what happens in each cell via 2-3 lines.

1. This is the standard python library import cell which fetches `mnist`, `Sequentiual`, `Dense` and `np_utils` from `keras`.
2. This uses the imported `mnist.load_data()` function to obtain the data, split into training and testing sets.
3. This cell shapes by flattening the images to vector format, normalizing the inputs and applies one hot encoding.
4. This cell creates and builds an interconnected 2 hidden layer neural network with adam optimizing.
5. Here we build and fit the model ( the assignment itself shows 10 epochs but we run this with 200 as suggested in the actual question. )
6. Finally we print the baseline error and evaluate the accuracy of the model.

| Neural Network Topology Setup    | GPU Train Runtime | TPU Train Runtime | CPU Train Runtime | Your CPU Train Runtime |
| -----------------------------    | ----------------- | ----------------- | ----------------- | ---------------------- |
| ANN-2-Hidden (200 Epochs) | 2m                | 12m               | 13m               | 1m (i9 10900K)         |

\pagebreak

### 1.4-1: Execute the Convolutional Neural Network script and describe for each cell below what happens in each cell via 2-3 lines.

| Neural Network Topology Setup    | GPU Train Runtime | TPU Train Runtime | CPU Train Runtime | Your CPU Train Runtime |
| -----------------------------    | ----------------- | ----------------- | ----------------- | ---------------------- |
| ANN-2-Hidden (200 Epochs) | 2m                | 12m               | 13m               | 1m (i9 10900K)         |
| CNN (20 Epochs) - 2 folds        | 7m                | 16m               | 14m               | 2m (i9 10900K)         |
| CNN (20 Epochs) - 5 folds        | 16m               | 55m               | 47m               | 8m (i9 10900K)         |
| CNN (20 Epochs) - 10 folds       | 32m               | 2h                | 1h                | 18m (i9 10900K)        |

1. This is the standard python library import cell which fetches several functions that we will be using in the later cells - this includes the keras optimizers `adam`, `SGD`, `RMSprop`
2. This provides:
  - The definition for loading the mnist dataset and reshaping it to a single channel format as well as one hot encoding it. The definition has the formatted dataset as a return value
  - Another definition for normalizing the pixel values. The definition has the updated intake set as a return value.
3. This cell provides a function to define a CNN model with a stochastic gradient descent optimizer and:
  - A convolution layer
  - A pooling layer
  - A flatten layer
  - Two interconnected relu and softmax layers
4. This cell provides a function to evaluate a model with k-fold cross-validation ( with a default of 5 folds. ) It also keeps a running history of the model fit.
5. This cell provides a function to plot the history generated by the function described in the above cell. It iterates through the histories provided and plots the cross entropy loss and classification accuracy.
6. This cell provides a function to plot the accuracy of the model.
7. This cell provides a wrapper function to run all the previously defined functions in order.
8. This cell calls the wrapper function to begin running the notebook.

\

### 1.4-2: Execute the Convolutional Neural Network script - changing the optimizers used in the process to use all three SGD, Adam, RMSProp. Shortly describe the optimizers in your words in 2-3 lines each.

**Difference between optimizers:**

* `SGD`: Stochastic gradient descent will search for the optimum of the cost function by using the outcome of its current location. The general risk with `SGD` is getting stuck in local minimums rather than the function optimum.
* `RMSProp`: The difference between `RMSProp` and `SGD` is that `RMSProp` will keep a running average of the magnitudes of recent steps and can use that knowledge to adjust the step size as needed to converge faster.
* `Adam`: `Adam` is an improvement upon `RMSProp` in that it also keeps a track of an exponentially decaying average of past gradients as well as the average of the magnitudes of recent gradients.

Even though these models are essentially improvements upon one another the parameters used still make a huge difference. As we can see below with simple defaults `Adam` and `RMSProp` do not necessarily outperform `SGD`.


**Using the default 10 epochs and 5 folds. (Adam and RMSProp are run with default parameters)**

| Optimizers | Error  | Time |
| -          | -      | -    |
| `SGD`      | 1.3%   | 6m   |
| `Adam`     | 1.875% | 6m   |
| `RMSProp`  | 1.468% | 10m  |

\pagebreak

### 1.5: Execute the Convolution Neural Network script - changing the Epochs for the training time and describe in your own words in 2-3 lines what exactly that means.

For this segment we update the epochs for each attempt. In general the epochs are an indication of how frequently the weightings of the model will be updated. This means the model will more frequently adjust the weightings but we also receive a penalty to the amount of time it takes to run the model. Something to keep in mind is that using too many epochs can lead to over-fitting so it is not necessarily a guaranteed way to increase model accuracy.


**Using `SGD` with the given notebook parameters and 5 folds.**

| Epochs | Error  | Time  |
| -      | -      | -     |
| 10     | 1.3%   | 6m    |
| 50     | 1.252% | 34m   |
| 100    | 1.262% | 1h8m  |
| 200    | 1.248% | 2h22m |
| 300    | 1.225% | 3h51m |
| 400    | 1.217% | 5h15m |

\

### 1.6: Execute the Convolution Neural Network script - changing the validation split ratio for the training time and describe in your own words in 2-3 lines what exactly that means.

We change the ratio between the training and test sets. The initial ratio for the mnist dataset is $\frac{1}{7}$ so the starting VAL_SPLIT is approximately 0.14.

The effect of shifting the split ratio is that if we the validation set is too small then our accuracy can have a large variance. On the other hand if our training set is too small then our model may lack data to effectively learn.


**Using `SGD` with the given notebook parameters, 10 epochs and 5 folds.**

| VAL_SPLIT | Error | Time  |
| -         | -     | -     |
| 0.0       | 1.302% | 8m    |
| 0.1       | 1.413% | 6m    |
| 0.2       | 1.233% | 7m    |
| 0.3       | 1.475% | 8m    |
| 0.4       | 1.583% | 5m    |
| 0.5       | 1.73% | 5m    |

\pagebreak

### 1.7: Execute the Convolution Neural Network script - changing the regularization ratio for the training time and describe in your own words in 2-3 lines what exactly that means.

Here we add an L1 `Lasso Regression` penalty to the convolutional layer. The more we use the Lasso penalty the more aggressive we are with feature selection, in some cases less important features are removed entirely.

**Using `SGD` with the given notebook parameters, 10 epochs and 5 folds.**

| Regularization | Error  | Time |
| -              | -      | -    |
| 0.0            | 1.3%   | 7m   |
| 0.1            | 3.593% | 7m   |
| 0.2            | 4.24%  | 7m   |
| 0.3            | 4.922% | 7m   |
| 0.4            | 5.208% | 7m   |
| 0.5            | 5.59%  | 7m   |

\

### 1.8: Execute the Convolution Neural Network script - changing the architecture by adding layers for the training time and describe in your own words in 2-3 lines what exactly that means. Does it help to improve the accuracy?

Adding additional more convolutional layers provides an opportunity to extract more features from the model. There is a limit to this effect where instead of extracting useful features we over-fit the data that exists instead.

Initially this should increase accuracy but eventually by adding more and more layers we will begin to see our error rate increase from over-fitting.


**Using `SGD` with the given notebook parameters, 10 epochs and 5 folds.**

| Deep Layers                 | Error  | Time |
| -                           | -      | -    |
| 1 more convolutional layer  | 1.253% | 12m  |
| 2 more convolutional layers | 1.062% | 17m  |
| 3 more convolutional layers | 1.21%  | 15m  |

