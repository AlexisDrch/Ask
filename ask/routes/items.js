
// add query functions
function getAllItems(req, res, next) {
  db.any('select * from item')
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retrieved ALL items'
        });
    })
    .catch(function (err) {
      return next(err);
    });
}

router.get('/api/items', getAllItems);